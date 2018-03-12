/**
 * 
 */
package org.filteredpush.nameparser.sciname;

import java.util.Map;

/**
 * @author mole
 *
 */
public class ScientificName {

	private String originalStringValue;
	
	private String genericEpithet;
	private String subgenericEpithet;
	private String specificEpithet;
	private String subspecificEpithet;
	private String rank;
	private String infraspecificEpithet;
	private String trivialEpithet;
	private String authorship;
	private boolean hybrid;
	private boolean namedHybrid;
	private boolean structuredHybrid;
	
	private String cleanParse;
	private String pathology;
	private String extraneousText;
	private String errorMessage;
	
	public ScientificName(Map<String,String> parseResult) { 
		if (parseResult.containsKey("Genus")) { 
			genericEpithet = parseResult.get("Genus");
		}
	}

	/**
	 * Obtain the value for the originalStringValue
	 *
	 * @return the originalStringValue
	 */
	public String getOriginalStringValue() {
		return originalStringValue;
	}

	/**
	 * Obtain the value for the genericEpithet
	 *
	 * @return the genericEpithet
	 */
	public String getGenericEpithet() {
		return genericEpithet;
	}

	/**
	 * Obtain the value for the subgenericEpithet
	 *
	 * @return the subgenericEpithet
	 */
	public String getSubgenericEpithet() {
		return subgenericEpithet;
	}

	/**
	 * Obtain the value for the specificEpithet
	 *
	 * @return the specificEpithet
	 */
	public String getSpecificEpithet() {
		return specificEpithet;
	}

	/**
	 * Obtain the value for the subspecificEpithet
	 *
	 * @return the subspecificEpithet
	 */
	public String getSubspecificEpithet() {
		return subspecificEpithet;
	}

	/**
	 * Obtain the value for the rank
	 *
	 * @return the rank
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * Obtain the value for the infraspecificEpithet
	 *
	 * @return the infraspecificEpithet
	 */
	public String getInfraspecificEpithet() {
		return infraspecificEpithet;
	}

	/**
	 * Obtain the value for the trivialEpithet
	 *
	 * @return the trivialEpithet
	 */
	public String getTrivialEpithet() {
		return trivialEpithet;
	}

	/**
	 * Obtain the value for the authorship
	 *
	 * @return the authorship
	 */
	public String getAuthorship() {
		return authorship;
	}

	/**
	 * Obtain the value for the hybrid
	 *
	 * @return the hybrid
	 */
	public boolean isHybrid() {
		return hybrid;
	}

	/**
	 * Obtain the value for the namedHybrid
	 *
	 * @return the namedHybrid
	 */
	public boolean isNamedHybrid() {
		return namedHybrid;
	}

	/**
	 * Obtain the value for the structuredHybrid
	 *
	 * @return the structuredHybrid
	 */
	public boolean isStructuredHybrid() {
		return structuredHybrid;
	}

	/**
	 * Obtain the value for the cleanParse
	 *
	 * @return the cleanParse
	 */
	public String getCleanParse() {
		return cleanParse;
	}

	/**
	 * Obtain the value for the pathology
	 *
	 * @return the pathology
	 */
	public String getPathology() {
		return pathology;
	}

	/**
	 * Obtain the value for the extraneousText
	 *
	 * @return the extraneousText
	 */
	public String getExtraneousText() {
		return extraneousText;
	}

	/**
	 * Obtain the value for the errorMessage
	 *
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
}
