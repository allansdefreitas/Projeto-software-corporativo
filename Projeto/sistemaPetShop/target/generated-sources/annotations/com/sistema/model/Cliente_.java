package com.sistema.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cliente.class)
public abstract class Cliente_ extends com.sistema.model.Usuario_ {

	public static volatile ListAttribute<Cliente, Cartao> cartao;
	public static volatile ListAttribute<Cliente, Pet> listaPet;

}

