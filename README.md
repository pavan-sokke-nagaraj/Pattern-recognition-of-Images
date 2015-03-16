# comp6731-patternrecognition-assignment3
Automatically exported from code.google.com/p/comp6731-patternrecognition-assignment3

# Pattern recognition of Images 

## Input: Images as standard computer keyboard character or integers. Hand written Images with characters or images
## Output: Geometrical features extracted from the each image and classification of the image to its character or integer.
Pattern Recognition process:
1.	Image acquisition: Read the input image using ImageIO java library.
2.	Image digitizing: Digitizing the input character image.
3.	Image binarization: Converts an image of up to 256 gray levels into a two-tone image represented by 0 and 1
4.	Size Normalization: Normalize the binary image to the size of 30 pixels height and 25 pixels of width(Size Normalization)
5.	Image Skeletonization: Apply Zhang-Suen Skeletonization  to skeletonize the binary image
6.	Feature extraction: Feature extraction and selection can be defined as extracting the most representative information from the raw data, which minimizes the within class pattern variability while enhancing the between class pattern variability 
Extract features from the skeletonized binary image
Features extracted (Geometrical features)
1.	Start/ end pixels
2.	Intersection points
3.	Horizontal lines
4.	Vertical lines
5.	Number of Holes
6.	Left diagonals
7.	Right Diagonal
7.	Image classification: Classify the image to its respective character or integer according to the extracted features. 












1.	Image 4 (Standard Computer Image )

(i)	Input: Image 4 
 

(ii)	Size Normalization: Output: Image after converting to binary and size normalization


 













(iii)	Skeletonization: Image after Zhang-Suen Skeletonization

 

(iv)	Feature Extraction and classification: Features extracted from Skeletonized image and
Image Classification

 


2.	Image ( Hand written 4 )
(i)	Input: Hand written Image 4
 

(ii)	Size Normalization: Output: Image after converting to binary and size normalization
 



(iii)	Skeletonization: Image after Zhang-Suen Skeletonization
 

(iv)	Feature Extraction and classification: Features extracted from Skeletonized image and image Classification
 




3.	Image ( Hand written 4 )
(i)	Input: Hand written Image 4
 

(ii)	Size Normalization: Output: Image after converting to binary and size normalization

 



(iii)	Skeletonization: Image after Zhang-Suen Skeletonization
 

(iv)	Feature Extraction and classification: Features extracted from Skeletonized image and image Classification
 


4.	Image (Standard Computer Image 8 )
(i)	Input: Image 8
 

(ii)	Size Normalization: Output: Image after converting to binary and size normalization

 



(iii)	Skeletonization: Image after Zhang-Suen Skeletonization

 

(iv)	Feature Extraction and classification: Features extracted from Skeletonized image and image Classification

 



5.	Image ( Hand written Image 8 )
(i)	Input: Image 8

 

(ii)	Size Normalization: Output: Image after converting to binary and size normalization


 









(iii)	Skeletonization: Image after Zhang-Suen Skeletonization
 
(iv)	Feature Extraction and classification: Features extracted from Skeletonized image and image Classification


 


6.	Image ( Computer Input Image 8 )
(i)	Input: Image A


 

(ii)	Size Normalization: Output: Image after converting to binary and size normalization
 







(iii)	Skeletonization: Image after Zhang-Suen Skeletonization
 
(iv)	Feature Extraction and classification: Features extracted from Skeletonized image and image Classification

 




7.	Image ( Hand written Image A )
(i)	Input: Image A
 


(ii)	Size Normalization: Output: Image after converting to binary and size normalization
 








(iii)	Skeletonization: Image after Zhang-Suen Skeletonization
 

(iv)	Feature Extraction and classification: Features extracted from Skeletonized image and image Classification
 





8.	Image ( Standard Computer input Image B )
(i)	Input: Image B

 

(ii)	Size Normalization: Output: Image after converting to binary and size normalization
 







(iii)	Skeletonization: Image after Zhang-Suen Skeletonization
 

(iv)	Feature Extraction and classification: Features extracted from Skeletonized image and image Classification
 




9.	Image ( Hand written input Image B )
(i)	Input: Image B
 

(ii)	Size Normalization: Output: Image after converting to binary and size normalization
 







(iii)	Skeletonization: Image after Zhang-Suen Skeletonization
 

(iv)	Feature Extraction and classification: Features extracted from Skeletonized image and image Classification
 





10.	Image (Hand written input Image B )
(i)	Input: Image B

 


(ii)	Size Normalization: Output: Image after converting to binary and size normalization
 









(iii)	Skeletonization: Image after Zhang-Suen Skeletonization
 

(iv)	Feature Extraction and classification: Features extracted from Skeletonized image and image Classification
 




Summary of Features classified according to the input images
Features	Start/End pixels	Intersections	Horizontal lines	Vertical lines	No of Holes	Left diagonals	Right Diagonals
Images	 	 	 	 	 	 	 
Computer Input Image 4	3	2	1	2	1	1	0
Hand written Image 4	3	2	1	1	1	1	0
Hand written Image 4	3	1	1	2	0	0	0
Classifying features for Image 4	EXACT 3	1 to 2	EXACT 1	1 to 2	0 to 1	0 to 1	EXACT 0
 	 	 	 	 	 	 	 
Computer Input Image 8	0	2	2	3	2	0	1
Hand written Image 8	1	3	2	0	2	0	1
Classifying features for Image 8	0 to 1	2 to 3	EXACT 2	0 to 3	EXACT 2	EXACT 0	0 to 1
 	 	 	 	 	 	 	 
Computer Input Image A	2	2	1	0	1	1	1
Hand written Image A	2	2	1	0	1	1	1
Classifying features for Image A	EXACT 2	EXACT 2	EXACT 1	EXACT 0	EXACT 1	EXACT 1	EXACT 1
 	 	 	 	 	 	 	 
Computer Input Image B	2	4	3	3	1	0	2
Hand written Image B	0	2	3	1	2	0	1
Hand written Image B	2	4	1	3	2	0	1
Classifying features for  Image B 	0 TO 2	2 TO 4	1 TO 3	1 TO 3	1 TO 2	EXACT 0	1 TO 2




Observations: 
1.	Able to convert image to binary array.
2.	Geometric features are extracted successfully from the images.
3.	Successful in classifying the images to their characters with the features extracted.
4.	Successfully able to recognize the input images as the characters by extracting the geometric features.
5.	Successful in using Zhang-Suen Skeletonization algorithm to skeletonize the standard input and hand written images.
Drawbacks:
1.	Geometric features are not consistent in hand written characters. Hence, Features extracted from hand written images may vary from image to image.
2.	Hand written images with cursive writing can be tough to recognize.
3.	Low density pixelated images have a problem in skeletonizing and extracting the features.
4.	Geometric features may vary according to the persons hand writing.
Character recognition system can be made more successful and accurate by:
1.	Feature extraction can be made more accurate by increasing the Threshold values of number of pixels to consider as a feature for horizontal, vertical and right, left diagonals.
 
2.	Extracting more geometric features such as:
a.	Length of all horizontal lines
b.	Length of all vertical lines
c.	Length of all right diagonal lines
d.	Length of all left diagonal lines
e.	Minor starters: created when pixel under consideration have more than two neighbors.
f.	Convex Area: Scalar that specifies the number of pixels in Convex Image.
g.	Orientation: It is the angle (in degrees ranging from -90 to 90 degrees) between the x-axis and the major axis of the ellipse that has the same second-moments as the region.
3.	Cursive hand written images can be recognized more by boxing the boundaries of each images character and rotating the image by an angle to extract features more accurately.
4.	Skeleton with broken lines can be extracted as feature to increase the accuracy of the image recognition.

REFERENCES
1)	Character Recognition Systems: A Guide for Students and Practitioners, by Mohamed Cheriet, Nawwaf Kharma, Cheng-Lin Liu, Ching Suen,Hoboken, New Jersey: John Wiley & Sons, Inc., 2007.

