#include <iostream>
#include <algorithm>
using namespace std;
int m,n;
int i[1010];
int y[1010];
int idone;
int ydone;
int sum;
int main(){
  cin >> m >> n;
  m--;
  n--;
  for(int x = 0; x<m; x++)
    cin >> i[x];
  for(int x = 0; x<n; x++)
    cin >> y[x];
  sort(i, i+m,greater<int>());
  sort(y, y+n,greater<int>());
  for(int x = 0; x<m+n; x++){
    if(i[idone]>y[ydone]){
      sum+=i[idone]*(ydone+1);
      idone++;
    }
    else{
      sum+=y[ydone]*(idone+1);
      ydone++;
    }
  }
  cout << sum;
}
