from sympy import *
import json

x, y, z = symbols("x y z")
ec = ""
t = ""
er = ""
rs = ""
with open("./bind.json", "r") as j:
    d = json.load(j)
    t = d["type"]
    ec = d["eval"]

if t == "integrate":
    try:
        rs = integrate(ec, x)
    except Exception as e:
        er = str(e)

    r = {"type": t, "eval": f"{ec}", "result": f"{rs}", "error": f"{er}"}

    with open("./bind.json", "w") as w:
        json.dump(r, w)

    if len(er) > 0:
        print("error")
    else:
        print("ok")
elif t == "derivate":
    try:
        rs = Derivative(ec, x, evaluate=True)
    except Exception as e:
        er = str(e)

    r = {"type": t, "eval": f"{ec}", "result": f"{rs}", "error": f"{er}"}

    with open("./bind.json", "w") as w:
        json.dump(r, w)

    if len(er) > 0:
        print("error")
    else:
        print("ok")