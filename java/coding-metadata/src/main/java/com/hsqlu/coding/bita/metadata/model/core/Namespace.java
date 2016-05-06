package com.hsqlu.coding.bita.metadata.model.core;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created: 2016/4/21.
 * Author: Qiannan Lu
 */
@Entity
@Table(name = "T_MD_ELEMENT")
@GenericGenerator(name = "ID", strategy = "uuid.hex")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Namespace<OWNER extends ModelElement> extends ModelElement<OWNER> implements Serializable {
    private static final long serialVersionUID = 7881652471436479434L;
}
