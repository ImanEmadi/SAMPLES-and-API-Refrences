import requests

# import json
import re
from typing import Match

targetURL = "https://www.duolingo.com"
u_inp = input(
    "Enter targetURL or skip to use defaultURL {} :".format(targetURL)
).strip()

if len(u_inp) > 0:
    targetURL = u_inp


headers = {
    "Content-type": "text/html;charset=UTF-8",
    "User-Agent": "GoogleOther",
    "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8",
    "Accept-Encoding": "gzip, deflate, br",
    "Accept-Language": "en-US,en;q=0.5",
    "Connection": "keep-alive",
}

res = requests.get(targetURL, headers=headers)


def metaHandler(meta):
    print(meta)


if res.status_code == 200:
    head = re.search(r"<head>.*?<\/head>", res.text, flags=re.I | re.M | re.S)
    if head == None:
        print("No HEAD Element was found!")
    else:
        _metaTags = re.findall(r"<meta.*?>", head.group(0))
        if _metaTags == None:
            print("No meta Elements were Found!")
        else:
            for meta in _metaTags:
                metaHandler(meta)
else:
    print(
        "Unexpected response code: {0} | Reason: {1}".format(
            res.status_code, res.reason
        )
    )
