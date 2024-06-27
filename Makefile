build: Servere.java Colorare.java Compresie.java Criptat.java Oferta.java
	javac Servere.java Colorare.java Compresie.java Criptat.java Oferta.java

run-p1: Servere.class
	java Servere

run-p2: Colorare.class
	java Colorare

run-p3: Compresie.class
	java Compresie

run-p4: Criptat.class
	java Criptat

run-p5: Oferta.class
	java Oferta

clean: *.class
	rm *.class