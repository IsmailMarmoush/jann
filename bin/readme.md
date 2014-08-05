Project Jann 
------------

JANN is an Artificial Neural Networks Library written in JAVA and licensed under GPL V3or later. The project is in preAlpha "Unstable versions 0.x" phase and lots of change is inevitable.

So far Jann is based on the following technologies:

Jann Core Library
The high performance JBlas Linear algebra Library ( Release 0.4 )

JGraph graph data structure library  ( Release 0.4 )

JFreeChart

Birt choice has been eliminated
Jann Cloud ( To be Launched in Sept 2012 )
JFreeChart

GWT, Smart GWT

Google App Engine
For Any Questions should be asked on stackoverflow.com don't forget to hash tag JANN and msg me with the link no need to comment here.

For Bugs, Feature Requests etc..  http://github.com/IsmailMarmoush/JANN/issues

I'll be glad to receive  coding help, algorithms, suggestions from experts. If you have an idea or project you think Jann fits you're very much welcome to use it under the licence.


High Performance.


Jannlib performance was a basic non-functional requirement when I started building it. The idea was originally CANN (C++ Artificial Neural Network). What differs Jann from many other Java ANN libraries is JBLAS a very promising Math library that dares to achieve very high speed of calculations and compete with C++ performance jblas is essentially a light-wight wrapper around the BLAS and LAPACK routines. These packages have originated in the Fortran community which explains their often archaic API. On the other hand modern implementations are hard to beat performance wise. jblas aims to make this functionality available to Java programmers such that they do not have to worry about writing JNI interfaces and calling conventions of Fortran code. Also in Jann I'm trying as much as possible to optimize the code while making it also readable.

Dynamic
Designing a network is no longer a problem. Neural networks comes in many different structures (competitive, feed forward and feed backward etc). So by using Graph Theory to structure the linkage between layers inside the network it self. Where the indices of layers are represented as the JGraph data structure vertex, you can connect any layers together; this way a training such as Back-propagation would be in its most easy and clear possible form and in easy steps you can know a layer's predecessor or successors.
Download

Jann Contributer:

You can fork it at github.com, and pull request me.

Jann for Developers:

You can download Javadoc, Src, or Deployed jars from https://github.com/IsmailMarmoush/JANN/downloads plug it in your project.

Jann Cloud for Engineers and Scholars:

Jann Cloud Web application might be launched in September 2012 God willing. The website will hold a friendly user interface. Jann Cloud will have similar functionality of MATLAB nntool, but not in design of course. There will be reports and charting engine I haven't decided yet but birt choice has been eliminated and currently trying JFreeChart, Neural network structure graphs.

Jann cloud for Eng and Sch. is mainly experimental for real time applications users can either choose to use Jann lib or ask for commercial web service account where they can communicate with it through web services or even on the cloud uploads.

Testimonials

SoftPedia.com JANN is a unique, open source, small, easy to use Artificial Neural Networks library.

History & Release Notes

Jann trials took hundreds of hours, I'm not a neural network expert or mathematician so I didn't have the required background, The moment I learn about an algorithm I run back to implement it in jann, but the more complex techniques get the more I become aware of similarities and ways to have more robust, general, and dynamic neural network training.

Before jann there was CANN a C++ version of Jann. Implementation of C++ applications is very powerful but it lacks the ease of deployment and update as you know that only for you to use a dynamic library (.SO) in linux would be a huge burden or if you tried to go through the MAKE FILE  hassle. though I found operator overloading very powerful technique I also found that sometimes you can just be ok without it.

JANN-0.4:
--------
Part 1
---------
Linear regression One/Multiple Variables
Linear regression Training:
Batch GD (single neuron)
Stochastic GD (single or nNeurons)
Feature Mapping with degrees
Feature Scaling with (STD, average)
Normal Equation (single neuron)
Dynamic Training through com.marmoush.jann.train.Train Class
Better Tests
*** Backpropagation will be deleted and replaced with better more clear algorithms
Part 2
--------

Models:
1- Logistic Regression One Class
2- Logistic Regression Multiple Classes

Training:
Logistic Regression GD
Regularized Linear Regression
Regularized Logistic Regression
Utils:
Random Initialization of theta

Part 3
-------
Models:
1- Logistic Regression Neural Network
2- Linear Regression Neural Network

Gradient Checking
Numerical Estimation of gradients
Neural Network Back propagation

Part 4
--------
Notes or Logic for Decision Making For handling errors :
1-Identify Problems with the help of (Graphs, tools)
2-Act upon on it (Manual ways, Automatic ways)
Part 5
--------
More Tests
More Graphs

JANN-0.3
--------
Graphed Network
Functors IFunctionable aka Java function pointers {IPerformance, Iweight, ITransfere}
Feed forward, perceptron, backpropagation trials

JANN-0.2
---------
Many network structure trials
Transfere Functions:
COMPET
HARDLIM
HARDLIMS
LOGSIG
LOGSIGDIFF
POSLIN
PURELIN
SATLIN
SATLINS
TANSIG
TANSIGDIFF
Performance Functions:
MSE, MAE, SSE
