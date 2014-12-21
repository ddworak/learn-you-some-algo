#include <iostream>
#include <cmath>
using namespace std;

unsigned short int w[65536];
unsigned int d,n;

struct node{
    unsigned int **m;
    unsigned int from;
    unsigned int to;
};

node t[65536];

inline unsigned int p(unsigned int i) { return (i-1) / 2; }
inline unsigned int lchild(unsigned int i) {return 2 * i+1;}
inline unsigned int rchild(unsigned int i) {return 2 * i + 2;}

void multiply(unsigned int m1,unsigned int m2, unsigned int r){
    unsigned int i=w[t[m1].from],j=w[t[m2].to],kk=w[t[m1].to];
    t[r].from=t[m1].from;
    t[r].to=t[m2].to;
    t[r].m=new unsigned int*[i];
    for(unsigned int x=0;x<i;x++){
        t[r].m[x]=new unsigned int[j];
        for(unsigned int y=0;y<j;y++){
            unsigned int s = 0;
            for(unsigned int k=0;k<kk;k++)s+=(t[m1].m[x][k]*t[m2].m[k][y])%d;
            t[r].m[x][y]=s%d;
        }
    }
}

node multiplynodes(node &t1,node &t2){
    node result;
    unsigned int i=w[t1.from],j=w[t2.to],kk=w[t1.to];
    result.from=t1.from;
    result.to=t2.to;
    result.m=new unsigned int*[i];
    for(int x=0;x<i;x++){
        result.m[x]=new unsigned int[j];
        for(unsigned int y=0;y<j;y++){
            unsigned int s = 0;
            for(unsigned int k=0;k<kk;k++)s+=(t1.m[x][k]*t2.m[k][y])%d;
            result.m[x][y]=s%d;
        }
    }
    return result;
}

void updateTree(unsigned int i){
    if(i>n-2)return;
    updateTree(lchild(i));
    updateTree(rchild(i));
    multiply(lchild(i),rchild(i),i);
}

node resultNode(unsigned int from,unsigned int to){
    unsigned int c=0,mid;
    while(t[c].from!=from){
        mid=(t[c].from+t[c].to+1)/2;
        if(from<mid)c=lchild(c);
        else c=rchild(c);
    }
    while(t[c].to>to)c=lchild(c);
    if(t[c].to==to)return t[c];
    node temp = resultNode(t[c].to,to);
    return multiplynodes(t[c],temp);
}
void change(unsigned int w,unsigned int i,unsigned int j,unsigned int r){
    unsigned int pos=n+w-1;
    t[pos].m[i][j]=r%d;
    pos=p(pos);
    while(pos!=0){
        multiply(lchild(pos),rchild(pos),pos);
        pos=p(pos);
    }
    multiply(lchild(0),rchild(0),0);
}
int main(){
    unsigned int k,ti,tj,x,temp_int;
    ios_base::sync_with_stdio(false);
    cin >> d >> n;
    unsigned int maxk=pow(2,ceil(log2(n))),realn = maxk;
    for(x=0;x<n;x++)cin >> w[x];
    unsigned int wtemp=w[x-1];
    while(x<realn)w[x++]=wtemp;
    for(x=0;x<n-1;x++){ //for each voiv
        k=realn+x-1;
        t[k].from=x;
        ti=w[x];
        t[k].to=x+1;
        tj=w[x+1];
        t[k].m = new unsigned int*[ti];
        for(unsigned int i=0;i<ti;i++){
            t[k].m[i]=new unsigned int[tj];
            for(unsigned int j=0;j<tj;j++){
                cin >> temp_int;
                t[k].m[i][j]=temp_int%d;
            }
        }

    }
    //for last one and dummies - unit matrices
    for(x;x<realn;x++){
        k=realn+x-1;
        t[k].from=x;
        ti=w[n-1];
        t[k].to=x+1;
        tj=w[n-1];
        t[k].m=new unsigned int*[ti];
        for(int i=0;i<ti;i++){
            t[k].m[i]=new unsigned int [tj];
            for(int j=0;j<tj;j++)t[k].m[i][j]= i==j?1:0;
        }
    }
    t[2*realn-2].to=realn-1; //last
    n=realn;
    //data is in
    updateTree(0);
    unsigned int w1,c1,w2,c2;
    char typ;
    cin >> typ >> w1 >> c1 >> w2 >> c2;
    node temp;
    while(typ!='e'){
        if(typ=='q') cout << resultNode(w1-1,w2-1).m[c1-1][c2-1] << endl;
        else change(w1-1,c1-1,w2-1,c2);
        cin >> typ >> w1 >> c1 >> w2 >> c2;
    }
}
