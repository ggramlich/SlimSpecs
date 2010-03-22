<?php
class Fixtures_Basics_EchoFixture
{
    public function echoString($string)
    {
        return $string;
    }

    public function echoInt($value)
    {
        return (int) $value;
    }

    public function echoDouble($value)
    {
        return (double) $value;
    }

    public function echoFloat($value)
    {
        return (float) $value;
    }
}
