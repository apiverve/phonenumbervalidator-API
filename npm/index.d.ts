declare module '@apiverve/phonenumbervalidator' {
  export interface phonenumbervalidatorOptions {
    api_key: string;
    secure?: boolean;
  }

  export interface phonenumbervalidatorResponse {
    status: string;
    error: string | null;
    data: PhoneNumberValidatorData;
    code?: number;
  }


  interface PhoneNumberValidatorData {
      country:         string;
      detectedCountry: string;
      countrycode:     number;
      numberNational:  number;
      extension:       null;
      isvalid:         boolean;
      type:            string;
      formatted:       Formatted;
  }
  
  interface Formatted {
      international: string;
      national:      string;
      rfc:           string;
      e164:          string;
  }

  export default class phonenumbervalidatorWrapper {
    constructor(options: phonenumbervalidatorOptions);

    execute(callback: (error: any, data: phonenumbervalidatorResponse | null) => void): Promise<phonenumbervalidatorResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: phonenumbervalidatorResponse | null) => void): Promise<phonenumbervalidatorResponse>;
    execute(query?: Record<string, any>): Promise<phonenumbervalidatorResponse>;
  }
}
