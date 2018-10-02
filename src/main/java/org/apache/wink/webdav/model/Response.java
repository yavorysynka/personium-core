/**
 * personium.io
 * Modifications copyright 2018 FUJITSU LIMITED
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * --------------------------------------------------
 * This code is based on Response.java of wink-webdav, and some modifications
 * for personium.io are applied by us.
 * --------------------------------------------------
 * The copyright and the license text of the original code is as follows:
 */
/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 *******************************************************************************/
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.1-b02-fcs
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2008.12.04 at 02:20:17 PM IST
//

package org.apache.wink.webdav.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.wink.common.http.HttpStatus;
import org.w3c.dom.Element;

/**
 * The <code>response</code> XML element per the WebDAV specification [RFC 4918]
 *
 * <pre>
 *    Name:       response
 *    Namespace:  DAV:
 *    Purpose:    Holds a single response describing the effect of a
 *    method on resource and/or its properties.
 *    Description: A particular href MUST NOT appear more than once as the
 *    child of a response XML element under a multistatus XML element.
 *    This requirement is necessary in order to keep processing costs for a
 *    response to linear time.  Essentially, this prevents having to search
 *    in order to group together all the responses by href.  There are,
 *    however, no requirements regarding ordering based on href values.
 *
 *    &lt;!ELEMENT response (href, ((href*, status)|(propstat+)),
 *    responsedescription?) &gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"href", "status", "propstat", "error", "responsedescription"})
@XmlRootElement(namespace = "DAV:", name = "response")
public class Response {

    @XmlElement(namespace = "DAV:", name = "href", required = true)
    protected List<String>   href;
    @XmlElement(namespace = "DAV:", name = "status")
    protected String         status;
    @XmlElement(namespace = "DAV:", name = "propstat")
    protected List<Propstat> propstat;
    @XmlElement(namespace = "DAV:", name = "error")
    protected Error          error;
    @XmlElement(namespace = "DAV:", name = "responsedescription")
    protected String         responsedescription;

    public Response() {
    }

    public Response(String href) {
        getHref().add(href);
    }

    /**
     * Gets the value of the href property.
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the href property.
     * <p>
     * For example, to add a new item, do as follows:
     *
     * <pre>
     * getHref().add(newItem);
     * </pre>
     * <p>
     * Objects of the following type(s) are allowed in the list {@link String }
     */
    public List<String> getHref() {
        if (href == null) {
            href = new ArrayList<String>();
        }
        return this.href;
    }

    /**
     * Gets the value of the status property.
     *
     * @return possible object is {@link String }
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value allowed object is {@link String }
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the propstat property.
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the propstat property.
     * <p>
     * For example, to add a new item, do as follows:
     *
     * <pre>
     * getPropstat().add(newItem);
     * </pre>
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Propstat }
     */
    public List<Propstat> getPropstat() {
        if (propstat == null) {
            propstat = new ArrayList<Propstat>();
        }
        return this.propstat;
    }

    /**
     * Get a Propstat instance contained within this response that has the
     * provided criteria.
     *
     * @param status the Http status code of the propstat to retrieve
     * @param description the description of the propstat to retrieve
     * @param error the error of the propstat to retrieve. Note that when
     *            searching for a matching propstat, errors are considered the
     *            same only if both errors are null.
     * @return the Propstat instance if a match is found, or null if no
     *         propstats matches all criteria
     */
    public Propstat getPropstat(int status, String description, Error error) {
        // try to find an existing propstat for given status, error and
        // description
        for (Propstat propstat : getPropstat()) {
            Error pError = propstat.getError();
            String pDescription = propstat.getResponsedescription();
            int pStatus = HttpStatus.valueOfStatusLine(propstat.getStatus()).getCode();

            // compare given propstat
            if (pStatus == status && pError == error && // ... do not compare
                                                        // errors - only if both
                                                        // are null (the same)
                ((pDescription == description) || (pDescription.equals(description)))) { //NOPMD
                return propstat;
            }
        }
        return null;
    }

    /**
     * Get a Propstat instance contained within this response that has the
     * provided criteria, or create a new Propstat instance with the provided
     * information if no propstat already exists.
     *
     * @param status the Http status code of the propstat to retrieve
     * @param description the description of the propstat to retrieve
     * @param error the error of the propstat to retrieve. Note that when
     *            searching for a matching propstat, errors are considered the
     *            same only if both errors are null.
     * @return
     */
    public Propstat getOrCreatePropstat(int status, String description, Error error) {
        Propstat propstat = getPropstat(status, description, error);
        if (propstat == null) {
            propstat = new Propstat();
            propstat.setProp(new Prop());
            propstat.setStatus(HttpStatus.valueOf(status).getStatusLine());
            propstat.setResponsedescription(description);
            propstat.setError(error);
            getPropstat().add(propstat);
        }
        return propstat;
    }

    /**
     * Gets the value of the error property.
     *
     * @return possible object is {@link Error }
     */
    public Error getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     *
     * @param value allowed object is {@link Error }
     */
    public void setError(Error value) {
        this.error = value;
    }

    /**
     * Gets the value of the responsedescription property.
     *
     * @return possible object is {@link String }
     */
    public String getResponsedescription() {
        return responsedescription;
    }

    /**
     * Sets the value of the responsedescription property.
     *
     * @param value allowed object is {@link String }
     */
    public void setResponsedescription(String value) {
        this.responsedescription = value;
    }

    /**
     * Add the provided property to the correct propstat element with an Http
     * status of OK.
     *
     * @see {@link #setProperty(Object, int, String, Error)}
     */
    public void setPropertyOk(Object property) {
        setProperty(property, HttpStatus.OK.getCode());
    }

    /**
     * Add the provided property to the correct propstat element with an Http
     * status of NOT FOUND.
     *
     * @see {@link #setProperty(Object, int, String, Error)}
     */
    public void setPropertyNotFound(Object property) {
        setProperty(property, HttpStatus.NOT_FOUND.getCode());
    }

    /**
     * Add the provided property to the correct propstat element with the
     * specified Http status.
     *
     * @see {@link #setProperty(Object, int, String, Error)}
     */
    public void setProperty(Object property, int status) {
        setProperty(property, status, null, null);
    }

    /**
     * Sets the provided property to the correct propstat element. The correct
     * propstat element is selected according to the provided status,
     * description and error parameters.
     * <p>
     * The following table details the possible Java types of every property
     * (note that the <code>DAV</code> namespace depicts the WebDAV namespace
     * "DAV:")
     * <p>
     * <table border="1">
     * <tr>
     * <td>DAV:creationdate</td>
     * <td>org.apache.wink.webdav.model.Creationdate</td>
     * </tr>
     * <tr>
     * <td>DAV:displayname</td>
     * <td>org.apache.wink.webdav.model.Displayname</td>
     * </tr>
     * <tr>
     * <td>DAV:getcontentlanguage</td>
     * <td>org.apache.wink.webdav.model.Getcontentlanguage</td>
     * </tr>
     * <tr>
     * <td>DAV:getcontentlength</td>
     * <td>org.apache.wink.webdav.model.Getcontentlength</td>
     * </tr>
     * <tr>
     * <td>DAV:getcontenttype</td>
     * <td>org.apache.wink.webdav.model.Getcontenttype</td>
     * </tr>
     * <tr>
     * <td>DAV:getetag</td>
     * <td>org.apache.wink.webdav.model.Getetag</td>
     * </tr>
     * <tr>
     * <td>DAV:getlastmodified</td>
     * <td>org.apache.wink.webdav.model.Getlastmodified</td>
     * </tr>
     * <tr>
     * <td>DAV:lockdiscovery</td>
     * <td>org.apache.wink.webdav.model.Lockdiscovery</td>
     * </tr>
     * <tr>
     * <td>DAV:resourcetype</td>
     * <td>org.apache.wink.webdav.model.Resourcetype</td>
     * </tr>
     * <tr>
     * <td>DAV:supportedlock</td>
     * <td>org.apache.wink.webdav.model.Supportedlock</td>
     * </tr>
     * <tr>
     * <td>any other</td>
     * <td>org.w3c.dom.Element</td>
     * </tr>
     * </table>
     * <p>
     * for example, if the property to set is
     * <code>DAV:getcontentlanguage</code> then the property should be an
     * instance of org.apache.wink.webdav.model.Getcontentlanguage.
     * <p>
     * if the property is <code>K:myprop</code> then the property should be an
     * instance of org.w3c.dom.Element
     *
     * @param property the property to add
     * @param status the status of the property to add
     * @param description the description of the property
     * @param error the error of the property
     */
    public void setProperty(Object property, int status, String description, Error error) {
        Propstat propstat = getOrCreatePropstat(status, description, error);
        Prop prop = propstat.getProp();
        if (property instanceof Creationdate) {
            prop.setCreationdate((Creationdate)property);
        } else if (property instanceof Displayname) {
            prop.setDisplayname((Displayname)property);
        } else if (property instanceof Getcontentlanguage) {
            prop.setGetcontentlanguage((Getcontentlanguage)property);
        } else if (property instanceof Getcontentlength) {
            prop.setGetcontentlength((Getcontentlength)property);
        } else if (property instanceof Getcontenttype) {
            prop.setGetcontenttype((Getcontenttype)property);
        } else if (property instanceof Getlastmodified) {
            prop.setGetlastmodified((Getlastmodified)property);
        } else if (property instanceof Getetag) {
            prop.setGetetag((Getetag)property);
        } else if (property instanceof Lockdiscovery) {
            prop.setLockdiscovery((Lockdiscovery)property);
        } else if (property instanceof Resourcetype) {
            prop.setResourcetype((Resourcetype)property);
        } else if (property instanceof Supportedlock) {
            prop.setSupportedlock((Supportedlock)property);
        } else if (property instanceof Element) {
            prop.getAny().add((Element)property);
        } else {
            throw new IllegalArgumentException("property"); //$NON-NLS-1$
        }
    }

}
