using System;
using System.Collections.Generic;
using System.Text;
using Newtonsoft.Json;

namespace APIVerve.API.PhoneNumberValidator
{
    /// <summary>
    /// Query options for the Phone Number Validator API
    /// </summary>
    public class PhoneNumberValidatorQueryOptions
    {
        /// <summary>
        /// The phone number to validate
        /// Example: 816-555-1017
        /// </summary>
        [JsonProperty("number")]
        public string Number { get; set; }

        /// <summary>
        /// The country code of the phone number (e.g., us, uk)
        /// Example: us
        /// </summary>
        [JsonProperty("country")]
        public string Country { get; set; }
    }
}
