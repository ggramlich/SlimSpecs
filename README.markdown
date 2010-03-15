SlimSpecs
=========

SlimSpecs is an attempt to standardize the behaviour of the 
various implementations of
[Robert C. Martin](http://www.objectmentor.com/omTeam/martin_r.html)'s
[Slim](http://fitnesse.org/FitNesse.UserGuide.SliM). 

It also demonstrates the language agnostic features of Slim.

Either start the fitnesse.jar from this directory or make sure that you use 
the -d option to point to this directory.

Here are some hints what you need to do to get the tests to run.

The ant script builds the SlimRunners from directories that
are positioned parallel to the SlimSpecs directory.

The dependency handling will be improved soon.

Java
----

fitnesse from http://github.com/unclebob/fitnesse

PHP
---

Make sure that you have a PHP CGI version installed.

phpslim from http://github.com/ggramlich/phpslim

JavaScript
----------

JsSlim from http://github.com/ggramlich/JsSlim

C# (Mono)
---------

The Slim runner is not automatically built yet, but are in fitnesse/fitSharp.
I will build that from http://github.com/jediwhale/fitsharp.

Fixtures are compiled with nant.

Ruby
----

Make sure you have a ruby installation.

rubyslim from http://github.com/unclebob/rubyslim

Planned:
--------

- Python with waferslim
- C with Cslim
- ... Any more?

