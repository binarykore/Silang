#include <stdio.h>
#include <string.h>

#define zout printf
#define test zabc()

char * key(){
	return("Decryption Key\r\n");
}

char * zabc(){
	return("ZABC..\r\n");
}