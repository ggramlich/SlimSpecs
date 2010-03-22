'''
Simple Echo fixture
'''
from waferslim.converters import convert_arg

class EchoFixture:
    ''' Simple fixture to echo a value back e.g. for variable substitution '''  
    def echo_string(self, value):
        ''' Echo back the value passed in as a str'''
        return value
        
    @convert_arg(to_type=int)
    def echo_int(self, value):
        return value

    @convert_arg(to_type=float)
    def echo_float(self, value):
        return value

    @convert_arg(to_type=float)
    def echo_double(self, value):
        return value
