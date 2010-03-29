fixtures.basics.EchoFixture = function() {
    this.echoString = function(string) {
        return string;
    }
    
    this.echoInt = function(value) {
        return parseInt(value);
    }
    
    this.echoDouble = function(value) {
        return JsSlim.Converter.floatToString(parseFloat(value));
    }
    
    this.echoFloat = function(value) {
        return this.echoDouble(value);
    }
}
