fixtures.basics.EchoFixture = function() {
    this.echoString = function(string) {
        return string;
    }
    
    this.echoInt = function(value) {
        return parseInt(value);
    }
    
    this.echoDouble = function(value) {
        return parseFloat(value)
    }
    
    this.echoFloat = function(value) {
        return this.echoDouble(value);
    }
}
