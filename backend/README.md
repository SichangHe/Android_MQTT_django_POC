# Django backend

- Connect and subscribe to the broker at start.
- Publish default message when `/publish/` is accessed.

## Set up

1. Manually install required packages.
1. `cd` here.
1. Run the migrations.

    ```shell
    python3 manage.py migrate
    ```

1. See if you can launch the test server.

    ```shell
    python3 manage.py runserver
    ```

## Visual Studio Code plugins

Please use these plugins.

- Code Spell Checker
- Django
- djLint
- Pylance
- Python
