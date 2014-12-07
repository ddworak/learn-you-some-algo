#include <iostream>
#include <vector>
#include <list>
#include <queue>
using namespace std;
int main(){
    ios_base::sync_with_stdio(0);
    int n,m,c1,c2;
    cin >> n >> m;
    int* source = new int[n+1];
    int* result = new int[n+1];
    vector<list<int> > ngh(n+1,list<int>());
    for(int i=0;i<=n;i++){
        source[i]=0;
        result[i]=0;
    }
    for(int i=1;i<=m;i++){
        cin >> c1 >> c2;
        ngh[c1].push_back(c2);
        ngh[c2].push_back(c1);
    }
    //data loaded
    int start=1;
    while(start<n+1){
    //cout << "Start: "<< start << " " << result[start] << endl;
    queue<int> tempq;
    tempq.push(start);
    int root=0,c;
    source[start]=start; //not sure
    list<int>::iterator it;
    while(!tempq.empty() && root==0){
        c=tempq.front();
        tempq.pop();
        //cout << "Went: " << c << endl;
        for(it=ngh[c].begin(); it!=ngh[c].end();it++)
            if(source[*it]==0){
                tempq.push(*it);
                source[*it]=c;
            }
            else if(*it!=source[c])root=c;
    }
    if(root==0){
        cout << "NIE";
        return 0;
    }
    //cout << root << " " << source[root] << endl;
    ngh[root].remove(source[root]);
    ngh[source[root]].remove(root);
    //c=source[root];
    result[root]=source[root];
    for(int i=1;i<=n;i++){
        source[i]=0;
    }
    tempq=queue<int>();
    tempq.push(root);
    while(!tempq.empty()){
        c=tempq.front();
        tempq.pop();
        //cout << "Went: " << c << endl;
        for(it=ngh[c].begin();it!=ngh[c].end();it++)
            if(result[*it]==0){
                //cout << *it << " ";
                tempq.push(*it);
                result[*it]=c;
            }
        //cout << endl;
    }
    start=1;
    while(start<n+1 && result[start]!=0)start++;
    }

    cout << "TAK\n";
    for(int i=1;i<n;i++)cout << result[i] << endl;
    cout << result[n];
}