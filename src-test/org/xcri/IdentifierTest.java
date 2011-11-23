/**
 * Copyright (c) 2011 University of Bolton
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this 
 * software and associated documentation files (the "Software"), to deal in the Software 
 * without restriction, including without limitation the rights to use, copy, modify, 
 * merge, publish, distribute, sublicense, and/or sell copies of the Software, and to 
 * permit persons to whom the Software is furnished to do so, subject to the following 
 * conditions:
 * The above copyright notice and this permission notice shall be included in all copies 
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE 
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, 
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE 
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.xcri;

import static org.junit.Assert.assertEquals;

import org.jdom.Element;
import org.junit.Test;
import org.xcri.common.Identifier;

public class IdentifierTest {
	
	@Test
	public void fromElement(){
		Element element = new Element("identifier", Namespaces.XCRI_NAMESPACE);
		element.setAttribute("type", "ukrlp:ukprn", Namespaces.XSI_NAMESPACE_NS);
		element.setText("TEST1");
		Identifier identifier = new Identifier();
		identifier.fromXml(element);
		assertEquals("TEST1", identifier.getValue());
		assertEquals("ukrlp:ukprn", identifier.getType());
	}

}