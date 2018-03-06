# Artifical-intelligence-Playfair-Cipher-Project-
Using Simulated Annealing to Break a Playfair Cipher

## Project Details
<p>The field of cryptanalysis is concerned with the study of ciphers, having as its objective the
identification of weaknesses within a cryptographic system that may be exploited to convert
encrypted data (cipher-text) into unencrypted data (plain-text). Whether using symmetric or
asymmetric techniques, cryptanalysis assumes no knowledge of the correct cryptographic key
or even the cryptographic algorithm being used.
Assuming that the cryptographic algorithm is known, a common approach for breaking a cipher
is to generate a large number of keys, decrypt a cipher-text with each key and then examine the
resultant plain-text. If the text looks similar to English, then the chances are that the key is a
good one. The similarity of a given piece of text to English can be computed by breaking the
text into fixed-length substrings, called n-grams, and then comparing each substring to an
existing map of n-grams and their frequency. This process does not guarantee that the outputted
answer will be the correct plain-text, but can give a good approximation that may well be the
right answer.</p>


## You are required to use the simulated annealing algorithm to break a Playfair Cipher. Your
## application should have the following minimal set of features:

#### • A menu-driven command line UI that enables a cipher-text source to be specified (a
file or URL) and an output destination file for decrypted plain-text.

#### • Decrypt cipher-text with a simulated annealing algorithm that uses a log-probability
and n-gram statistics as a heuristic evaluation function.
A full description of the Playfair Cipher, a simulated annealing algorithm for breaking ciphers
and n-gram statistics are provided below as supplementary material. Note that extra marks will
only be given for features that directly relate to the content covered in this module.

## The Playfair Cipher
<p>The Playfair Cipher is a symmetric polygram substitution cipher invented by the Victorian
scientist Sir Charles Wheatstone in 1854 (of Wheatstone Bridge fame). The cipher is named
after his colleague Lord Playfair, who popularised and promoted the encryption system. Due to
its simplicity, the Playfair Cipher was used at a tactical level by both the British and US forces
during WWII and is also notable for its role in the rescue of the crew of PT-109 in the Pacific
in 1943.
Polygram substitution is a classical system of encryption in which a group of n plain-text letters
is replaced as a unit by n cipher-text letters. In the simplest case, where n = 2, the system is
called digraphic and each letter pair is replaced by a cipher digraph. The Playfair Cipher uses
digraphs to encrypt and decrypt from a 5x5 matrix constructed from a sequence key of 25
unique letters. Note that the letter J is not included.
For the purposes of this assignment, it is only necessary to implement the decrypt functionality
of the Playfair Cipher. It should be noted however, that the 5x5 matrix described below can be
augmented with auxiliary data structures to reduce access times to O(1). For example, the letter
‘A’ has a Unicode decimal value of 65. Thus, an int array called rowIndices could store the
matrix row of a char val at rowIndices[val – 65]. The same principle can be used for columns…
The encryption / decryption process works on diagraphs as follows:


<img src="https://github.com/gtonra89/DocumentSimilarityProject/blob/master/34524665-39cc66f2-f094-11e7-9bdb-e405f29ece83.PNG" align="middle"/>






The Playfair Cipher suffers from the following three basic weaknesses that can be exploited to
break the cipher, even with a pen and paper:
1. Repeated plain-text digrams will create repeated cipher-text digrams.
2. Digram frequency counts can reveal the most frequently occurring English digrams.
3. The most frequently occurring cipher-text letters are likely to be near the most frequent

English letters, i.e. E, T, A and O in the 5x5 square. This helps to reconstruct the 5x5
square.
We will be exploiting weakness (2) in this assignment. Note that these weaknesses rely on
repetition and frequency counts and, in the absence of cribs, require enough cipher-text to reveal
patterns. In practice, this implies that at least 200 characters of cipher-text are available. </p>



