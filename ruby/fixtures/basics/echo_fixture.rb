module Fixtures
  module Basics
    class EchoFixture
      def echo_string(string)
        string
      end
      
      def echo_int(value)
        return value.to_i;
      end
      
      def echo_float(value)
        return value.to_f;
      end
      
      def echo_double(value)
        return value.to_f;
      end
    end
  end
end


