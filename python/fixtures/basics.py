'''
Simple Echo fixture
'''

class EchoFixture:
    ''' Simple fixture to echo a value back e.g. for variable substitution '''  
    def echo_string(self, value):
        ''' Echo back the value passed in as a str'''
        return value
