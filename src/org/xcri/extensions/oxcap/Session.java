package org.xcri.extensions.oxcap;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Element;
import org.xcri.Extension;
import org.xcri.Namespaces;
import org.xcri.core.Presentation;
import org.xcri.exceptions.InvalidElementException;
import org.xcri.presentation.End;
import org.xcri.presentation.Start;
import org.xcri.presentation.Venue;
import org.xcri.util.lax.Lax;

public class Session extends OxcapElement implements Extension {
	
	private Log log = LogFactory.getLog(Session.class);
	
	private Start start;
	private End end;
	private Venue[] venues;
	
	/**
	 * @return the element name
	 */
	@Override
	public String getName() {
		return "session";
	}
	
	/* (non-Javadoc)
	 * @see org.xcri.types.CommonType#toXml()
	 */
	@Override
	public Element toXml() {
		Element element = super.toXml();
		if (this.getStart() != null) element.addContent(this.getStart().toXml());
		if (this.getEnd() != null) element.addContent(this.getEnd().toXml());
		
		if (this.getVenues() != null){
			for (Venue venue: this.getVenues()){
				element.addContent(venue.toXml());
			}
		}
		return element;
	}
	
	@Override
	public void fromXml(Element element) throws InvalidElementException {
		super.fromXml(element);
		if (Lax.getChildQuietly(element, "start", Namespaces.MLO_NAMESPACE_NS, log) !=null){
			Start start = new Start();
			try {
				start.fromXml(Lax.getChildQuietly(element, "start", Namespaces.MLO_NAMESPACE_NS, log));
				this.setStart(start);
			} catch (InvalidElementException e) {
				log.warn("presentation : skipping invalid start element: "+e.getMessage());
			}
		}
		if (Lax.getChildQuietly(element, "end", Namespaces.XCRI_NAMESPACE_NS, log)!=null){
			End end = new End();
			try {
				end.fromXml(Lax.getChildQuietly(element, "end", Namespaces.XCRI_NAMESPACE_NS, log));
				this.setEnd(end);
			} catch (InvalidElementException e) {
				log.warn("presentation : skipping invalid end element: "+e.getMessage());
			}
		}
		
		if (Lax.getChildrenQuietly(element, "venue", Namespaces.XCRI_NAMESPACE_NS, log)!=null){
			ArrayList<Venue> venues = new ArrayList<Venue>();
			for (Element venueElement: Lax.getChildrenQuietly(element, "venue", Namespaces.XCRI_NAMESPACE_NS, log)){
				Venue venue = new Venue();
				venue.fromXml(venueElement);
				venues.add(venue);
			}
			this.setVenues(venues.toArray( new Venue[venues.size()]));
		}
	}
	
	/**
	 * @return the start
	 */
	public Start getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(Start start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public End getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(End end) {
		this.end = end;
	}
	
	/**
	 * @return the venues
	 */
	public Venue[] getVenues() {
		return venues;
	}

	/**
	 * @param venues the venues to set
	 */
	public void setVenues(Venue[] venues) {
		this.venues = venues;
	}
	
	
}
