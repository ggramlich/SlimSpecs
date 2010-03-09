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

I plan to write an ant script that automates some of these points.

Java
----

Open the project in Eclipse and build it to make sure that the
fixtures are compiled in the bin folder.

Put a fitnesse.jar into the fitnesse folder. I left this out, because
otherwise it would consume a lot of memory from my github repository.

PHP
---

Make sure that you have a PHP CGI version installed.

JavaScript
----------

Should run out of the box.

C# (Mono)
---------

Compile the project so that a
mono/SlimSpecs/bin/Release/SlimSpecs.dll is generated.

Ruby
----

Make sure you have a ruby installation.

Planned:
--------

- Python with waferslim
- C with Cslim
- ... Any more?

