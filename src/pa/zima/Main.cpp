#include <iostream>
#include <vector>
#include <algorithm>
#define MAXN 500000

using namespace std;

vector<pair<int,short> > edges[MAXN+1];

int improvement(int s=1,int parent=-1){
    static int sum[MAXN+1];
    int top[3]={0,0,0},most=0;
    for(unsigned int i=0;i<edges[s].size();i++)
        if(edges[s][i].first != parent){
            most=max(most,improvement(edges[s][i].first,s));
            top[2]=sum[edges[s][i].first]+edges[s][i].second;
            sort(top,top+3,greater<int>());
        }
    sum[s]= max(top[0],top[1]);
    return max(most,top[0]+top[1]);
}

int main(){
    ios_base::sync_with_stdio(0);
    long long result = 0;
    int n,c1,c2,w;
    cin >> n;

    for(int i=0; i<n-1;i++){
        cin >> c1 >> c2 >> w;
        result+=w+(w%2);
        if(w&1){
            edges[c1].push_back(make_pair(c2,1));
            edges[c2].push_back(make_pair(c1,1));
        }
        else{
            edges[c1].push_back(make_pair(c2,-1));
            edges[c2].push_back(make_pair(c1,-1));
        }
    }
    cout << result - improvement();
}
