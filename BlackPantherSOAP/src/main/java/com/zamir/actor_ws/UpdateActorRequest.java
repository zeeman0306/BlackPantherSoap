//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.07.05 at 03:50:32 PM EDT 
//


package com.zamir.actor_ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actorInfo" type="{http://www.zamir.com/actor-ws}actorInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "actorInfo"
})
@XmlRootElement(name = "updateActorRequest")
public class UpdateActorRequest {

    @XmlElement(required = true)
    protected ActorInfo actorInfo;

    /**
     * Gets the value of the actorInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ActorInfo }
     *     
     */
    public ActorInfo getActorInfo() {
        return actorInfo;
    }

    /**
     * Sets the value of the actorInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActorInfo }
     *     
     */
    public void setActorInfo(ActorInfo value) {
        this.actorInfo = value;
    }

}
