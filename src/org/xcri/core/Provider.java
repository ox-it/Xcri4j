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

package org.xcri.core;

import java.util.ArrayList;

import org.jdom.Element;
import org.jdom.Namespace;
import org.xcri.Namespaces;
import org.xcri.provider.Location;
import org.xcri.types.CommonType;

public class Provider extends CommonType{
	
	private Course[] courses;
	private Location location;
	
	
	
	/* (non-Javadoc)
	 * @see org.xcri.types.Common#toXml()
	 */
	@Override
	public Element toXml() {
		Element element = super.toXml();
		
		//
		// Add courses
		//
		for (Course course: courses){
			element.addContent(course.toXml());
		}
		
		//
		// Add location
		//
        if (this.getLocation() != null) element.addContent(this.getLocation().toXml());
        
		return element;
	}

	/* (non-Javadoc)
	 * @see org.xcri.types.Common#fromXml(org.jdom.Element)
	 */
	@Override
	public void fromXml(Element element) {
		super.fromXml(element);
		
		//
		// Add children
		//
		ArrayList<Course> courses = new ArrayList<Course>();
		for (Object obj : element.getChildren("provider", Namespaces.XCRI_NAMESPACE_NS)){
			Course course = new Course();
			course.fromXml((Element)obj);
			courses.add(course);
		}
		this.setCourses(courses.toArray(new Course[courses.size()]));
		
		if(element.getChild("location") != null){
			Location location = new Location();
			location.fromXml(element.getChild("location"));
			this.setLocation(location);
		}
	}

	/* (non-Javadoc)
	 * @see org.xcri.types.XcriElement#getNamespace()
	 */
	@Override
	public Namespace getNamespace() {
		return Namespaces.XCRI_NAMESPACE_NS;
	}

	/* (non-Javadoc)
	 * @see org.xcri.types.XcriElement#getName()
	 */
	@Override
	public String getName() {
		return "provider";
	}

	/**
	 * @return the courses
	 */
	public Course[] getCourses() {
		return courses;
	}
	
	/**
	 * @param courses the courses to set
	 */
	public void setCourses(Course[] courses) {
		this.courses = courses;
	}
	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
	
	

}