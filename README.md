# 光雾-Linght-mist
淡淡薄雾，引领你我。   
【光雾】是一个免费的Java同启软件，意在帮助用户同时启动多个软件。   
应用场景：   
- 在一个Docker内同时启动多个MC服务端

## 使用
- 下载[光雾](https://github.com/Linght-mist/Linght-mist/releases/download/v1.0.0/Linght-mist.jar)
- 在执行目录下创建config文件夹，并在其中创建config.json。
```json
[
    {
        "name": "填入名称",
        "dir": "填入工作目录",
        "command": "填入启动命令"
    }
]
```
- 运行jar文件即可。