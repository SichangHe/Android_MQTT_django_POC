import json

from django.http import JsonResponse

from backend.mqtt import client


def publish_message(request):
    # request_data = json.loads(request.body)
    request_data = json.loads(r"""{"topic": "django/mqtt", "msg": "Hi, Django."}""")
    rc, mid = client.publish(request_data["topic"], request_data["msg"])
    return JsonResponse({"code": rc})
