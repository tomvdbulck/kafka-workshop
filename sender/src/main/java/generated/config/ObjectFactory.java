//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.04.12 at 01:37:31 PM CEST 
//


package generated.config;

import java.math.BigInteger;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated.config package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Mivconfig_QNAME = new QName("", "mivconfig");
    private final static QName _TijdLaatsteConfigWijziging_QNAME = new QName("", "tijd_laatste_config_wijziging");
    private final static QName _LveNr_QNAME = new QName("", "lve_nr");
    private final static QName _KmpRsys_QNAME = new QName("", "Kmp_Rsys");
    private final static QName _Meetpunt_QNAME = new QName("", "meetpunt");
    private final static QName _Rijstrook_QNAME = new QName("", "Rijstrook");
    private final static QName _XCoordEPSG31370_QNAME = new QName("", "X_coord_EPSG_31370");
    private final static QName _YCoordEPSG31370_QNAME = new QName("", "Y_coord_EPSG_31370");
    private final static QName _LengtegraadEPSG4326_QNAME = new QName("", "lengtegraad_EPSG_4326");
    private final static QName _BreedtegraadEPSG4326_QNAME = new QName("", "breedtegraad_EPSG_4326");
    private final static QName _Ident8_QNAME = new QName("", "Ident_8");
    private final static QName _VolledigeNaam_QNAME = new QName("", "volledige_naam");
    private final static QName _BeschrijvendeId_QNAME = new QName("", "beschrijvende_id");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated.config
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TMivconfig }
     * 
     */
    public TMivconfig createTMivconfig() {
        return new TMivconfig();
    }

    /**
     * Create an instance of {@link TMeetpunt }
     * 
     */
    public TMeetpunt createTMeetpunt() {
        return new TMeetpunt();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TMivconfig }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TMivconfig }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "mivconfig")
    public JAXBElement<TMivconfig> createMivconfig(TMivconfig value) {
        return new JAXBElement<TMivconfig>(_Mivconfig_QNAME, TMivconfig.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "tijd_laatste_config_wijziging")
    public JAXBElement<XMLGregorianCalendar> createTijdLaatsteConfigWijziging(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_TijdLaatsteConfigWijziging_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "lve_nr")
    public JAXBElement<BigInteger> createLveNr(BigInteger value) {
        return new JAXBElement<BigInteger>(_LveNr_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "Kmp_Rsys")
    public JAXBElement<String> createKmpRsys(String value) {
        return new JAXBElement<String>(_KmpRsys_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TMeetpunt }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TMeetpunt }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "meetpunt")
    public JAXBElement<TMeetpunt> createMeetpunt(TMeetpunt value) {
        return new JAXBElement<TMeetpunt>(_Meetpunt_QNAME, TMeetpunt.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "Rijstrook")
    public JAXBElement<String> createRijstrook(String value) {
        return new JAXBElement<String>(_Rijstrook_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "X_coord_EPSG_31370")
    public JAXBElement<String> createXCoordEPSG31370(String value) {
        return new JAXBElement<String>(_XCoordEPSG31370_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "Y_coord_EPSG_31370")
    public JAXBElement<String> createYCoordEPSG31370(String value) {
        return new JAXBElement<String>(_YCoordEPSG31370_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "lengtegraad_EPSG_4326")
    public JAXBElement<String> createLengtegraadEPSG4326(String value) {
        return new JAXBElement<String>(_LengtegraadEPSG4326_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "breedtegraad_EPSG_4326")
    public JAXBElement<String> createBreedtegraadEPSG4326(String value) {
        return new JAXBElement<String>(_BreedtegraadEPSG4326_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "Ident_8")
    public JAXBElement<String> createIdent8(String value) {
        return new JAXBElement<String>(_Ident8_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "volledige_naam")
    public JAXBElement<String> createVolledigeNaam(String value) {
        return new JAXBElement<String>(_VolledigeNaam_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "beschrijvende_id")
    public JAXBElement<String> createBeschrijvendeId(String value) {
        return new JAXBElement<String>(_BeschrijvendeId_QNAME, String.class, null, value);
    }

}
