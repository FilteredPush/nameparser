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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class for representing components of a scientific name (for access to parse results from NameParse).
 * 
 * @author mole
 *
 */
public class ScientificName {

	private String originalStringValue;
	private String checkedStringValue;
	
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
	private List<String> pathologies;
	private String extraneousText;
	private String errorMessage;
	
	private List<ScientificName> hybridElements;
	
	private int epithetCount = 0;
	
	public ScientificName(Map<String,String> parseResult) {
		pathologies = new ArrayList<String>();
		hybridElements = new ArrayList<ScientificName>();
		cleanParse = true;
		if (parseResult.containsKey("OriginalValue")) { 
			originalStringValue = parseResult.get("OriginalValue");
		}
		if (parseResult.containsKey("CheckedValue")) { 
			checkedStringValue = parseResult.get("CheckedValue");
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
				String genericBit = "";
				for (int i=1; i<3; i++) { 
				    Map<String,String> bits = new HashMap<String,String>();
				    String key = "Genus"+Integer.toString(i);
				    if (parseResult.containsKey(key)) { 
				    	bits.put("Genus", bits.get(key));
				    	genericBit = "";
				    } else {
				    	if (i>1) { 
				    		if (genericBit.length()>0) { 
				    	        bits.put("Genus", genericBit);
				    		} else { 
				    	        bits.put("Genus", genericEpithet);
				    		}
				    	}
				    }
				    key = "Species"+Integer.toString(i);
				    if (parseResult.containsKey(key)) { 
				    	bits.put("Species", bits.get(key));
				    }		
				    key = "Subspecies"+Integer.toString(i);
				    if (parseResult.containsKey(key)) { 
				    	bits.put("Subspecies", bits.get(key));
				    }		
				    key = "Authorship"+Integer.toString(i);
				    if (parseResult.containsKey(key)) { 
				    	bits.put("Authorship", bits.get(key));
				    }
				    ScientificName child = new ScientificName(bits);
				    hybridElements.add(child);
				}
				
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
			String pathology = parseResult.get("Pathology");
			if (pathology.contains("|")) { 
			   pathologies.addAll(Arrays.asList(pathology.split("|")));
			} else { 
				pathologies.add(pathology);
			}
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
	public boolean isCleanParse() {
		return cleanParse;
	}

	/**
	 * Obtain the value for the pathology
	 *
	 * @return the pathology
	 */
	public String getPathology() {
		return pathologies.toString();
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

	/**
	 * Obtain the number of epithets composing the name (generic, specific, and below),
	 * not including subgenus.
	 *
	 * @return the epithetCount
	 */
	public int getEpithetCount() {
		return epithetCount;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		if (cleanParse) { 
			if (this.genericEpithet!=null) { result.append(genericEpithet).append(" "); } 
			if (this.specificEpithet!=null) { result.append(specificEpithet).append(" "); } 
			if (this.subspecificEpithet!=null) { result.append(subspecificEpithet).append(" "); } 
			if (this.infraspecificEpithet!=null) { result.append(infraspecificEpithet).append(" "); } 
			if (this.authorship!=null) { result.append(authorship); } 
		} else { 
			result.append("Problem Parsing: ").append(this.originalStringValue);
		}
		return result.toString().trim();
	}
	
	public String toCanonicalName() { 
		StringBuilder result = new StringBuilder();
		if (cleanParse) { 
			if (this.genericEpithet!=null) { result.append(genericEpithet).append(" "); } 
			if (this.specificEpithet!=null) { result.append(specificEpithet).append(" "); } 
			if (this.subspecificEpithet!=null) { 
			    if (this.rank!=null && this.infraspecificEpithet==null) { result.append(rank).append(" "); } 
				result.append(subspecificEpithet).append(" "); 
			} 
			if (this.infraspecificEpithet!=null) { 
			    if (this.rank!=null) { result.append(rank).append(" "); } 
				result.append(infraspecificEpithet).append(" "); } 
			if (this.authorship!=null) { result.append(authorship); } 
		} else { 
			result.append(this.originalStringValue);
		}
		return result.toString().trim();		
	}
}
