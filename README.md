# Punctured Hadamard Code On Image
This project is the slight modification to hadamard coding scheme called Punctured Hadamard Codd, implementation on image. Program asks you to introduce number of bit error and also how many codewords you wish to affect through those bit error when transmitted through a virtual channel.
</br>
Hadamard coding scheme is one of the oldest coding scheme which has very good error correcting and error detecting capibility but terrible Code rate. Here image is taken for applying this coding scheme. Color image is 3 channel so total number of messages
to be encoded are image x height x 3 (no. of pixels x no. of channels). This program uses python for some imange manipulation and java for coding theory implementaiton. Few of the code snippets are from [3].


## Usage
To run the program `python processImage.py` in the terminal will start program and user will be prompted to enter certain values. </br>

<p align="center">
   <img src="https://github.com/emkay-git/Hadamard-Code-On-Image/blob/master/images/console.png"> 
</p>
Output for various values of codewords which are affected. Top left is original test image, top right is for 10,000 codewords affected, bottom left is for 40,000 codewords affected, bottom left is 80,000 codewords affected.

<p align="center">
<img height="350" width="350" hspace="10" src="https://github.com/emkay-git/Hadamard-Code-On-Image/blob/master/images/one.png" style="float: left; width: 30%; margin-right: 1%; margin-bottom: 0.5em;"><img height="350" width="350" hspace="10" src="https://github.com/emkay-git/Hadamard-Code-On-Image/blob/master/images/40k.png" style="float: left; width: 30%; margin-right: 1%; margin-bottom: 0.5em;"><p style="clear: both;">
</p>


<p align="center">
<img height="350" width="350" hspace="10" src="https://github.com/emkay-git/Hadamard-Code-On-Image/blob/master/images/60k.png" style="float: left; width: 30%; margin-right: 1%; margin-bottom: 0.5em;"><img height="350" width="350" hspace="10" src="https://github.com/emkay-git/Hadamard-Code-On-Image/blob/master/images/80k.png" style="float: left; width: 30%; margin-right: 1%; margin-bottom: 0.5em;"><p style="clear: both;">
</p>

## Suggested Improvement(s)
* Program runs a little slow at the moment, better way for generating Hadamard matrix and pixel manipulaiton may be done.


## Resources
1. [UIC](http://homepages.math.uic.edu/~leon/mcs425-s08/handouts/Hadamard_codes.pdf)
2. [Wiki](https://en.wikipedia.org/wiki/Hadamard_code)
3. [https://github.com/rick7661/HadamardCoder/blob/master/Hadamard.java](https://github.com/rick7661/HadamardCoder/blob/master/Hadamard.java)
