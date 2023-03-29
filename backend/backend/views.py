import json

from django.http import HttpRequest, JsonResponse

from backend.mqtt import client


def publish_message(request: HttpRequest):
    body = r"""{"topic": "django/mqtt", "msg": "Hi, Django."}"""
    if request.body:
        body = request.body
    request_data = json.loads(body)
    rc, mid = client.publish(request_data["topic"], request_data["msg"])
    return JsonResponse({"code": rc})
