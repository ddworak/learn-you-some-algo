#include <iostream>
using namespace std;

int p[1000000];
int d[1000000];
int s[1000000];
bool res[1000000];

int main(){
    ios_base::sync_with_stdio(false);
    int n,psum=0,dsum=0;
    cin >> n;
    for(int i=0;i<n;i++){
        cin >> p[i] >> d[i];
        psum+=p[i];
        dsum+=d[i];
    }
    if(psum<dsum){
        for(int i=0; i<n;i++)cout << "NIE\n";
        return 0;
    }
    //looking for "start" base
    s[0]=0;
    for(int i=1;i<n;i++){
        s[i]=s[i-1]+p[i-1]-d[i-1];
    }
    s[0]=s[n-1]+p[n-1]-d[n-1];
    int mini=0;
    for(int i=1;i<n;i++)if(s[i]<s[mini])mini=i;
    //mini - index of "start" base
    res[mini]=true;
    int snow=0;
    int i;
    if(mini==0)i=n-1;
    else i=mini-1;
    for(i;i!=mini;i= i==0?n-1:i-1){
        snow+=p[i]-d[i];
        if(snow>=0){
            res[i]=true;
            snow=0;
        }
    }
    //looking for "start" base 2
    s[n-1]=0;
    for(int i=n-2;i>=0;i--){
        s[i]=s[i+1]+p[i+1]-d[i];
    }
    s[n-1]=s[0]+p[0]-d[n-1];
    mini=0;
    for(int i=1;i<n;i++)if(s[i]<s[mini])mini=i;
    //mini - index of "start" base 2
    res[mini]=true;
    snow=0;
    if(mini==n-1)i=0;
    else i=mini+1;
    int t;
    for(i;i!=mini;i= (i==(n-1)?0:i+1)){
        t=(i==0?n-1:i-1);
        snow+=p[i]-d[t];
        if(snow>=0){
            res[i]=true;
            snow=0;
        }
    }
    for(int i=0; i<n;i++)cout << (res[i]?"TAK\n":"NIE\n");
}