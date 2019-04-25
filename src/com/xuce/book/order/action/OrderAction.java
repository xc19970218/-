package com.xuce.book.order.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xuce.book.cart.vo.Cart;
import com.xuce.book.cart.vo.CartItem;
import com.xuce.book.order.service.OrderService;
import com.xuce.book.order.vo.Order;
import com.xuce.book.order.vo.OrderItem;
import com.xuce.book.user.vo.User;

public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	private Order order = new Order();

	public Order getModel() {
		return order;
	}
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	// ע��OrderService
	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	// ���ɶ�����ִ�еķ���:
	public String saveOrder() {

		// ����Service������ݿ����Ĳ���:
		// Order order = new Order();
		// ���ö������ܽ��:�������ܽ��Ӧ���ǹ��ﳵ���ܽ��:
		// ���ﳵ��session��,��session�ܻ�ù��ﳵ��Ϣ.
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		if (cart == null) {
			this.addActionMessage("��!����û�й���!");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		// ���ö�����״̬
		order.setState(1); // 1:δ����.
		// ���ö���ʱ��
		order.setOrdertime(new Date());
		// ���ö��������Ŀͻ�:
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if (existUser == null) {
			this.addActionMessage("��!����û�е�¼!");
			return "msg";
		}
		order.setUser(existUser);
		// ���ö������:
		for (CartItem cartItem : cart.getCartItems()) {
			// ���������Ϣ�ӹ������õ�.
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);

			order.getOrderItems().add(orderItem);
		}
		orderService.save(order);
		// ��չ��ﳵ:
		cart.clearCart();

		// ҳ����Ҫ���Զ�����Ϣ:
		// ʹ��ģ�������� ���п��Բ�ʹ��ֵջ������
		// ActionContext.getContext().getValueStack().set("order", order);

		return "saveOrder";
	}
}
