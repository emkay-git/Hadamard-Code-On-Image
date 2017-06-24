import cv2
import os
import numpy as np
import matplotlib.pyplot as plt


def process(image):
	img  = cv2.imread(image)
	print img.shape

	
	## this is to extract each pixel value and rgb values from each pixel then writing it to bin.txt
	bin = ''
	for i in range(0,img.shape[0]):
		for j in range(0,img.shape[1]):
			for k in range(0,3):
				
				
				bin +=  "{0:08b}".format(img[i][j][k])+"\n"
				
	f = open("bin.txt", "w")
	f.write(bin)
	f.close()


	##taking inputs for channel simulation
	print "Test image for the project is shown.\nThere are total "+str(img.shape[0]*img.shape[1]*3)+" number of message words. (Calculated as height*width*3)\n"
	x = input("Enter the number of codewords to change (Put in orders of 10,000 to see visible changes): ")
	y = input("Enter the number of bit error to introduce (To see distortion, put more than 31 as error correcting capibility is 31 or less): ")
	os.system("javac Hadamard.java && java Hadamard "+str(x) +" "+ str(y))
	

	##reading the values from decoded.txt and creating output image out of it.. to see the effect of channel
	c = 0
	outImage=np.empty((img.shape[0],img.shape[1],3),np.uint8)
	lines = [line.rstrip('\n') for line in open('decoded.txt')]

	for i in range(0,img.shape[0]):
		for j in range(0,img.shape[1]):
			for k in range(0,3):
				
				# print img[i][j][k]," "
				outImage[i][j][k] = int(lines[c],2)	
				c+=1;

	
		
	
	print "Input image on the left and Output image after channel transmission simiulation on the right"
	
	
	plot_image = np.concatenate((img, outImage), axis=1)

	plt.imshow(cv2.cvtColor(plot_image, cv2.COLOR_BGR2RGB))
	plt.show()


	# cv2.imshow("image",outImage)
	# cv2.waitKey(0)

process("test.jpg")