# Hadamard Code On Image
This project is the hadamard coding scheme implementation on image. Program asks you to introduce number of bit error and also
how many codewords you wish to affect through those bit error when transmitted through a virtual channel.

## Impelementation Details
Hadamard coding scheme is one of the oldest coding scheme which has a terrible code rate but it has very good error correcting and error 
detecting capibility. Here image is taken for applying this coding scheme. Color image is 3 channel so total number of messages
to be encoded are image x height x 3 (no. of pixels x no. of channels). This program uses python for some imange manipulation and java 
for coding theory implementaiton. Few of the code snippets are from [3].

## Preview


## Improvement
* Program runs a little slow at the moment, better way for generating Hadamard matrix and pixel manipulaiton may be done.


## Resources
1. [UIC](http://homepages.math.uic.edu/~leon/mcs425-s08/handouts/Hadamard_codes.pdf)
2. [Wiki](https://en.wikipedia.org/wiki/Hadamard_code)
3. [https://github.com/rick7661/HadamardCoder/blob/master/Hadamard.java](https://github.com/rick7661/HadamardCoder/blob/master/Hadamard.java)
