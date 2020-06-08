## Introduction
JANN is a Java Artificial Neural Network library.

## History
* I wrote Jann a year after my graduation, first commit was in 2011.
* Due to work and travel, also finding about Apache Spark, the project was abandoned.
* Today in 2020 almost 9 years, I decided to revisit, with no expectations. 

## Technologies used
* Java 14 with preview features enabled
* [jbom](https://github.com/memoria-io/jbom) based
* [jutils](https://github.com/memoria-io/jutils) based

## Release notes
### Jann v0.1.0
* Using maven
* Code clean up
* Using Java 14, Jbom, and Jutils

## Legacy Release notes
### JANN-0.4:
**Part 1**
Linear regression One/Multiple Variables
Linear regression Training:
Batch GD (single neuron)
Stochastic GD (single or nNeurons)
Feature Mapping with degrees
Feature Scaling with (STD, average)
Normal Equation (single neuron)
Dynamic Training through Train Class
Better Tests

**Part 2**
Models:

* Logistic Regression One Class
* Logistic Regression Multiple Classes

Training:

* Logistic Regression GD
* Regularized Linear Regression
* Regularized Logistic Regression

Utils:

* Random Initialization of theta

**Part 3**

Models:
1- Logistic Regression Neural Network
2- Linear Regression Neural Network

Gradient Checking
Numerical Estimation of gradients
Neural Network Back propagation

**Part 4**
Notes or Logic for Decision Making For handling errors :

1. Identify Problems with the help of (Graphs, tools)
2. Act upon on it (Manual ways, Automatic ways)

**Part 5**

* More Tests 
* More Graphs

### JANN-0.3
* Graphed Network
* Functors IFunctionable aka Java function pointers {IPerformance, Iweight, ITransfere}
* Feed forward, perceptron, backpropagation trials

### JANN-0.2

* Many network structure trials
* Transfere Functions:
* COMPET
* HARDLIM
* HARDLIMS
* LOGSIG
* LOGSIGDIFF
* POSLIN
* PURELIN
* SATLIN
* SATLINS
* TANSIG
* TANSIGDIFF
* Performance Functions:
* MSE, MAE, SSE
