import cv2
import os
import numpy as np
def process(image):
	img  = cv2.imread(image)
	print img.shape
	cv2.imshow("img",img);
	cv2.waitKey(0)
	print type(img)
	bin = ''
	for i in range(0,img.shape[0]):
		for j in range(0,img.shape[1]):
			for k in range(0,3):
				
				# print img[i][j][k]," "
				bin +=  "{0:08b}".format(img[i][j][k])+"\n"
				# msg = "java Hadamard.java "+img[i][j][k]
				# print msg
				# type(img[i][j][k])
				
			# print "\n"
		# print "\n"
	# print bin
	f = open("bin.txt", "w")
	f.write(bin)
	f.close()

	os.system("javac Hadamard.java && java Hadamard ")
	
	c = 0
	outImage=np.empty((img.shape[0],img.shape[1],3),np.uint8)
	print outImage.shape
	print type(outImage)
	lines = [line.rstrip('\n') for line in open('decoded.txt')]

	for i in range(0,img.shape[0]):
		for j in range(0,img.shape[1]):
			for k in range(0,3):
				
				# print img[i][j][k]," "
				outImage[i][j][k] = int(lines[c],2)	
				c+=1;

	
		
	# print "inside"
	# os.system("java Hadamard > out.txt")
	cv2.imshow("image",outImage)
	cv2.waitKey(0)
process("test.jpg")