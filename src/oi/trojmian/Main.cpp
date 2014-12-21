#include <iostream>
#define ull long long
using namespace std;
ull pot3[35];
ull suma[35];
void count(){
    suma[0]=1;
    pot3[0]=1;
    for(int i=1;i<=34;i++){
        pot3[i]=pot3[i-1]*3;
        suma[i]=suma[i-1]+pot3[i];
    }
}

ull szukajS(ull m){
    ull i=0;
    while(suma[i]<m)i++;
    return i;
}
int solveX(ull z,ull m,ull n);
int solveY(ull z,ull m,ull n) {
    ull sumaz= (z==0?0:suma[z-1]);
    ull w = pot3[z];
    ull p = 2*w;
    ull s = p+sumaz;
    if(m<p)return solveY(z-1,m-w,n);
    if(m>s){
        bool koniec = false;
        while(!koniec){
            koniec = true;
            if(n<2*(m-s)) return solveY(z-1,m-p,n);
            if(n<w)return 0;
            n-=w;
            koniec=false;
        }
    }
    else {
        if(n<=2*(m-p))return solveX(z-1,m-p,n);
        if(n<w) return 0;
        n-=w;
        if(n<=2*(m-p))return (solveX(z-1,m-p,n)*2)%3;
        return 0;
    }
}
int solveX(ull z,ull m,ull n){
    ull w=pot3[z];
    ull sumaz=(z==0?0:suma[z-1]);
    if(z<=0)return 1;
    if(m<=sumaz){
        return solveX(z-1,m,n);
    }
    if(m>=w){
        bool koniec = false;
        while(!koniec){
            koniec = true;
            if(n<=2*(m-w))return solveX(z-1,m-w,n);
            if(n<w)return 0;
            n-=w;
            koniec = false;
        }
    }
    else {
        if(n<=2*m-w)return solveY(z-1,m,n);
        if(n<w)return 0;
        if(n<=2*m)return solveY(z-1,m,2*m-n);
        return 0;
    }
}

int solve(ull m,ull n){
    return solveX(szukajS(m),m,n);
}
int main(){
    ios_base::sync_with_stdio(false);
    ull m,n;
    count();
    int k;
    cin >> k;
    for(int i=0;i<k;i++){
        cin >> m;
        cin >> n;
        cout << solve(m,n) << endl;
    }
}
