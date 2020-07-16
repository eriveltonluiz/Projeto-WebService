package com.project.demo.model.enums;

public enum PedidoStatus {

	WAITING_PAYMENT(1), PAID(2), SHIPPED(3), DELIVERED(4), CANCELED(5);

	private int code;

	private PedidoStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static PedidoStatus valueOf(int code) {

		for (PedidoStatus ps : PedidoStatus.values()) {
			if (ps.getCode() == code) {
				return ps;
			}
		}

		throw new IllegalArgumentException("Código inválido");
	}
}
