# FileSystem
A simplified linux like file system.

Pentru implementarea comenzilor, am o interfata Command pe care o implementeaza 
doua clase, una pentru comenzile ce lucreaza cu fisiere, iar cealalalta pentru
comenzile ce lucreaza cu useri. Comenzile concrete mostenesc una dintre cele
2 clase.

Pentru instantierea comenzilor folosesc o clasa CommandFactory ce verifica
numele comenzii si instantieaza clasa potrivita.

Fisierele le-am gandit sub forma arborescenta cu radacina root (/). Acestea
respecta design pattern-ul Composite deoarecece am o clasa Entity abstracta ce este
mostenita de 2 clase, una File si una Directory, iar Directory contine o lista
de Entity-uri ce reprezinta subfolderele si fisierele continute de acesta.
