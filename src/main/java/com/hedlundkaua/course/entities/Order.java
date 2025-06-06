package com.hedlundkaua.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hedlundkaua.course.entities.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//temos que fazer o relacionamento entre pedido e cliente para o JPA transformar o codigo em chaves estrangeiras no BD
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	
	//para dizer explicitamente que estou gravand no BD um numero inteiro, mas isso é só interno na classe order
	private Integer orderStatus;

	
	@ManyToOne //para instruir o JPA para transformar isso em chave estranjeira (muitos para um)
	@JoinColumn(name = "client_id") //para dar o nome a chave estrangeira no BD/ na tabela de pedidos no BD vamos ter uma chave estrangeira chamada client id que vai ter o id do usuario associado a esse pedido
	private User client;
	
	//quando fazers a chamada de um pedido automaticamente o JPA também carrega o cliente associado a ele.
	
	//com o JsonIgnore aqui ele faz com que quando fazemos uma requisição ele retorna o user e seus pedidos
	//e os pedidos são carregados, porque o jackson serializa o Json ele que solicita os pedidos do BD e o JPA buscou
	
	
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();
	
	//para pegar da classe payment a anotação OneToOne
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL) // no caso um para um estamos mapeando as duas entidade para ter o mesmo ID
	//, se o pedido for codigo 5 o pagamento tambem deve ter pagamaneto 5, e no caso de mapear um para um no mesmo id isso é obrigato 
	private Payment payment;
	
	public Order(){
	}

	public Order(Long id, Instant moment,OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if(orderStatus != null) {			
		this.orderStatus = orderStatus.getCode();
		}
	}
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Set<OrderItem> getItems(){
		return items;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", moment=" + moment + ", orderStatus=" + orderStatus + ", client=" + client + "]";
	}
}
