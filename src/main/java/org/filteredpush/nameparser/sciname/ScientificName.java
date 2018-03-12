/** ScientificName.java 
 * 
 * Copyright 2018 President and Fellows of Harvard College
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
 */
package org.filteredpush.nameparser.sciname;

import java.util.Map;

/**
 * A class for representing components of a scientific name (for access to parse results from NameParse).
 * 
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
	private String subsequentCitation;
	private boolean hybrid;
	private boolean namedHybrid;
	private boolean structuredHybrid;
	
	private boolean cleanParse;
	private String pathology;
	private String extraneousText;
	private String errorMessage;
	
	private int epithetCount = 0;
	
	public ScientificName(Map<String,String> parseResult) {
		cleanParse = true;
		if (parseResult.containsKey("OriginalValue")) { 
			originalStringValue = parseResult.get("OriginalValue");
		}
		
		if (parseResult.containsKey("Genus")) { 
			genericEpithet = parseResult.get("Genus");
			epithetCount++;
		}
		if (parseResult.containsKey("Subgenus")) { 
			subgenericEpithet = parseResult.get("Subgenus");
			rank = "subgenus";
			// epithet count is not incremented.
		}
		if (parseResult.containsKey("Species")) { 
			specificEpithet = parseResult.get("Species");
			trivialEpithet = specificEpithet;
			rank = "species";
			epithetCount++;
		}
		if (parseResult.containsKey("Subpecies")) { 
			subspecificEpithet = parseResult.get("Subpecies");
			trivialEpithet = subspecificEpithet;
			rank = "subspecies";
			epithetCount++;
		}
		if (parseResult.containsKey("Infraspecies")) { 
			infraspecificEpithet = parseResult.get("Infraspecies");
			trivialEpithet = infraspecificEpithet;
			rank = "infrasubspecific";
			epithetCount++;
		}
		if (parseResult.containsKey("Rank")) { 
			rank = parseResult.get("Rank");
		}
		
		if (parseResult.containsKey("Authorship")) { 
			authorship = parseResult.get("Authorship");
		}		
		if (parseResult.containsKey("SubsequentCitation")) { 
			subsequentCitation = parseResult.get("SubsequentCitation");
		}		
		if (parseResult.containsKey("NamedHybrid")) { 
			if (parseResult.get("NamedHybrid").equalsIgnoreCase("true")) { 
				namedHybrid = true;
				hybrid = true;
			}
		}		
		if (parseResult.containsKey("Hybrid")) { 
			if (parseResult.get("Hybrid").equalsIgnoreCase("true")) { 
				structuredHybrid = true;
				hybrid = true;
			}
		}			
		
		if (parseResult.containsKey("RemovedExtra")) { 
			extraneousText = parseResult.get("RemovedExtra");
			cleanParse = false;
		}
		if (parseResult.containsKey("Error")) { 
			errorMessage = parseResult.get("Error");
			cleanParse = false;
		}		
		if (parseResult.containsKey("Pathology")) { 
			pathology = parseResult.get("Pathology");
			cleanParse = false;
		}		
		
		//TODO: Support list of hybrid components/structured hybrid
		
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
	 * Obtain the value for the subsequentCitation
	 *
	 * @return the subsequentCitation
	 */
	public String getSubsequentCitation() {
		return subsequentCitation;
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
	public boolean getCleanParse() {
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
