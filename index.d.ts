declare module '@apiverve/phonenumbervalidator' {
  export interface phonenumbervalidatorOptions {
    api_key: string;
    secure?: boolean;
  }

  export interface phonenumbervalidatorResponse {
    status: string;
    error: string | null;
    data: any;
    code?: number;
  }

  export default class phonenumbervalidatorWrapper {
    constructor(options: phonenumbervalidatorOptions);

    execute(callback: (error: any, data: phonenumbervalidatorResponse | null) => void): Promise<phonenumbervalidatorResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: phonenumbervalidatorResponse | null) => void): Promise<phonenumbervalidatorResponse>;
    execute(query?: Record<string, any>): Promise<phonenumbervalidatorResponse>;
  }
}
