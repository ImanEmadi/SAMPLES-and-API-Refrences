# ADB related docs, tips and examples

## ADB with NoxPlayer (windows)

### Find which port is used by NoxPlayer

**Via the task manager**

- In `taskManager->details` and look for `NoxVMHandle.exe`.
- use the `PID` to find the associated port:

```
netstat -aon | grep {PID}
```

**Or you can use the command line:**

```shell
tasklist | grep NoxVMHandle.exe
```

### Connect adb to the NoxPlayer's port

```shell
adb connect 127.0.0.1:{PORT}
```

### If ADB gets disconnected periodically, write a .bat file and run in the background:

```bat
@echo off
adb connect 127.0.0.1:62025

:check_connection
adb devices | find "127.0.0.1:62025" >nul 2>&1
if errorlevel 1 (
    echo ADB is disconnected. Reconnecting...
    adb connect 127.0.0.1:62025
    timeout /t 3 >nul
    goto check_connection
) else (
    echo ADB is connected.
    timeout /t 3 >nul
    goto check_connection
)
```
