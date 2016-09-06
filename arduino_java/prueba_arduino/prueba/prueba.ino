
#include <ModbusRtu.h>

String input;

String au={"1 3 8 2"};
long an;
long an0;
long an1;
char an2;
int tempus;
char b;
char id='1';
char fcr='3';
char fcw='F';
int vc;
String a;
int ang;
char x;
char y;
char z;
int dato1;
int dato2;
int dato3;
int dato4;

void io_setup();
void io_poll() ;

void setup(){
 
  io_setup(); // I/O settings
  //b=0;

  // start communication
  Serial.begin( 9600 );
  tempus = millis() + 100;
  digitalWrite(13, HIGH );
}
 
void loop(){
  if (Serial.available()>0){
 
    input = Serial.readString();
 
    if (input=="1 3"){
 
      //digitalWrite(13, HIGH); //Si el valor de input es 1, se enciende el led
      Serial.println("no se que pasa");
 
    }
 
    else
 
    {
 
      //digitalWrite(13, LOW); //Si el valor de input es diferente de 1, se apaga el LED
      Serial.println("no se que pasa 2");
 
    }

    x=input[0];
y=input[2];
if (a[4]=='A'){
  
  a[4]=':';
  }else if(a[4]=='B'){
     a[4]=';';
    
    }else if(a[4]=='C'){
    
     a[4]='<';
    }else if(a[4]=='D'){
     a[4]='=';
    
    }else if(a[4]=='E'){
     a[4]='>';
    
    }else if(a[4]=='F'){
    
     a[4]='?';
    }

z=a[4];

if (a[6]=='A'){
  
  a[6]=':';
  }else if(a[6]=='B'){
     a[6]=';';
    
    }else if(a[6]=='C'){
    
     a[6]='<';
    }else if(a[6]=='D'){
     a[6]='=';
    
    }else if(a[6]=='E'){
     a[6]='>';
    
    }else if(a[6]=='F'){
    
     a[6]='?';
    }
if (a[7]=='A'){
  
  a[7]=':';
  }else if(a[7]=='B'){
     a[7]=';';
    
    }else if(a[7]=='C'){
    
     a[7]='<';
    }else if(a[7]=='D'){
     a[7]='=';
    
    }else if(a[7]=='E'){
     a[7]='>';
    
    }else if(a[7]=='F'){
    
     a[7]='?';
    }
an0 = a[6]-48;
an1=an0*16;
an2=a[7]-48;    
an= an1+an2;
 
if(x==id){
  Serial.println(x);
    if(y==fcr){
         Serial.println(y);
         io_poll();
      
        //Serial.println("no se que pasa");
        Serial.print('0');
        Serial.print(id);
        Serial.print(' ');
        Serial.print('0');
        Serial.print(fcr);
        Serial.print(' ');
        Serial.print('0');
        Serial.print(b,HEX);
         Serial.print(' ');
         Serial.print('0');
        Serial.print('0');
         Serial.print(' ');
           Serial.print('0');
         Serial.print('0');
         Serial.print(' ');
         
       }else if(y==fcw){
          
            digitalWrite( 6, bitRead( a[4], 0 ));
            digitalWrite( 7, bitRead( a[4], 1 ));
            digitalWrite( 8, bitRead( a[4], 2 ));
            digitalWrite( 9, bitRead( a[4], 3 ));
            
            
            analogWrite( 10, an );
            Serial.print(an,DEC);Serial.print(' ');Serial.print(' ');
            Serial.print(an1,DEC);Serial.print(' ');Serial.print(' ');
            Serial.print(an2,DEC);Serial.print(' ');Serial.print(' ');
            Serial.print(z,BIN);    Serial.print(' ');Serial.print(' ');
            delay(2000);    
          }


        
      }
    
    }
 
  
}


void io_setup() {
  // define i/o
  pinMode(2, INPUT);
  pinMode(3, INPUT);
  pinMode(4, INPUT);
  pinMode(5, INPUT);
  pinMode(6, OUTPUT);
  pinMode(7, OUTPUT);
  pinMode(8, OUTPUT);
  pinMode(9, OUTPUT);
  pinMode(10, OUTPUT);
 
  digitalWrite(6, LOW );
  digitalWrite(7, LOW );
  digitalWrite(8, LOW );
  digitalWrite(9, LOW );
  digitalWrite(13, HIGH ); // this is for the UNO led pin
  analogWrite(10, 0 );

}

/**
 *  Link between the Arduino pins and the Modbus array
 */
void io_poll() {


  if (digitalRead( 2 )== HIGH){
    dato1=1;
    }else if(digitalRead( 2 )== LOW){
    dato1=0;
    }
  if (digitalRead( 3 )== HIGH){
     dato2=2;
    }else if(digitalRead( 3 )== LOW){
     dato2=0;
    }
    
   if (digitalRead( 4 )== HIGH){
    dato3=4;
    }else if(digitalRead( 4 )== LOW){
    dato3=0;
    }
   if (digitalRead( 5 )== HIGH){
     dato4=8;
    }else if(digitalRead( 5 )== LOW){
    dato4=0;
    }

    b=(dato1+dato2+dato3+dato4);
     

}
