#include <cstdio>
#include <string>

using namespace std;

char rybCh[] = {'R', 'Y', 'B'}, gvoCh[] = {'G', 'V', 'O'};

int n, ryb[3], gvo[3];

string calc() {
  string res = "";

  for(int i = 0; i < 3; i++)
    ryb[i] -= gvo[i];

  if(ryb[0] == 0 && ryb[1] == 0 && ryb[2] == 0) { // special case
    if((gvo[0] > 0) + (gvo[1] > 0) + (gvo[2] > 0) == 1) {
      for(int i = 0; i < 3; i++) {
        while(gvo[i] > 0) {
          res.push_back(rybCh[i]);
          res.push_back(gvoCh[i]);
          gvo[i]--;
        }
      }
      return res;
    }
  }

  int firsti = -1, lasti = -1;
  while(res.size() < n) {
    int maxi = -1, maxv = 0;
    if(firsti >= 0 && firsti != lasti && ryb[firsti] > maxv) {
      maxi = firsti; maxv = ryb[firsti];
    }
    for(int i = 0; i < 3; i++) {
      if(i != lasti && ryb[i] > maxv) { maxi = i; maxv = ryb[i]; }
    }
    if(maxi == -1) return "IMPOSSIBLE";

    res.push_back(rybCh[maxi]);
    ryb[maxi]--;
    while(gvo[maxi] > 0) {
      res.push_back(gvoCh[maxi]);
      res.push_back(rybCh[maxi]);
      gvo[maxi]--;
    }
    if(firsti < 0) firsti = maxi;
    lasti = maxi;
  }
  return res[0] == res[n - 1] ? "IMPOSSIBLE" : res;
}

int main() {
  int t; scanf("%d\n", &t);
  for(int tc = 1; tc <= t; tc++) {
    scanf("%d %d %d %d %d %d %d\n",
          &n, &ryb[0], &gvo[2], &ryb[1], &gvo[0], &ryb[2], &gvo[1]);
    printf("Case #%d: %s\n", tc, calc().c_str());
  }
  return 0;
}