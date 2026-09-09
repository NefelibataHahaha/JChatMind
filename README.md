# 知行 AI（KnowFlow）

基于 Spring AI 的智能体与知识库问答平台。“知”代表知识检索，“行”代表工具调用与任务执行。

本项目基于程序员 Carl 的 JChatMind 项目进行个性化开发，保留原项目 MIT 版权与许可声明，详见 [LICENSE](LICENSE)。当前功能包含原项目能力与后续改动，不将全部功能归为个人原创。

## 当前能力

- Agent 执行：通过 Think-Execute 循环组织模型推理、手动工具调用和后续对话。
- 工具管理：支持数据库、文件、邮件、知识检索等工具；使用 `internalToolExecutionEnabled(false)` 手动编排工具执行。
- 知识库：Markdown 文档解析、分块、向量生成及 PostgreSQL/pgvector 距离检索。
- 多模型：通过 `ChatClientRegistry` 管理聊天模型客户端。
- 实时展示：通过 SSE 向 React 前端推送执行状态、内容和失败通知。

功能以当前源码为准；本项目未在此声明并发容量、响应延迟或检索准确率指标。

## 技术栈与目录

- 后端：Java 17、Spring Boot 3.5、Spring AI、MyBatis、PostgreSQL/pgvector。
- 前端：React 19、TypeScript、Vite、Ant Design。

| 目录 | 内容 |
| --- | --- |
| `knowflow/` | Maven 后端工程 |
| `ui/` | 前端应用 `knowflow-ui` |
| `examples/` | 独立 HTML 示例 |
| `.github/workflows/verify.yml` | 后端编译、上下文测试和前端构建 |

Java 包名为 `io.github.nefelibatahahaha.knowflow`，启动类为 `KnowFlowApplication`。

核心调用路径：

`ChatMessageController` → `ChatMessageFacadeServiceImpl` → `ChatEvent` → `ChatEventListener` → `AgentRuntimeFactory.create()` → `AgentRuntime.run()`

## 本地运行

1. 准备 Java 17、Node.js/npm 和 PostgreSQL/pgvector，以及已有业务表结构。聊天与向量生成还需要相应模型服务。
2. 参考根目录 `.env.example` 配置环境变量，或在 `knowflow/src/main/resources/application-local.yaml` 中配置本地参数。该文件被 Git 忽略，勿提交真实密钥。
3. 当前配置包含 `local` profile。`.env.example` 是配置参考，Spring Boot 不会自动读取根目录 `.env`，需要通过终端或 IDE 注入环境变量。
4. 从后端目录启动：

```powershell
cd knowflow
.\mvnw.cmd spring-boot:run
```

5. 在另一个终端启动前端：

```powershell
cd ui
npm ci
npm run dev
```

前端当前访问 `http://localhost:8080/api`，浏览器地址以 Vite 启动输出为准。

## 改名与兼容说明

- 产品名：知行 AI（KnowFlow）；Maven artifactId 和 Spring 应用名：`knowflow`。
- 后端目录由 `jchatmind/` 改为 `knowflow/`，在 IDEA 中重新加载 `knowflow/pom.xml`，启动类使用 `KnowFlowApplication`，工作目录使用 `knowflow/`。
- 核心类使用 `AgentRuntime` / `AgentRuntimeFactory`，前端布局组件使用 `AppLayout`。
- 数据库默认名称仍为 `jchatmind`，继续通过 `DB_URL` 配置，以兼容现有数据；此次改名不执行数据库迁移。
- 文档目录仍为后端工作目录下的 `data/documents`，已有文档随模块保留，历史知识库内容不自动替换。
- API 路径、SSE 事件字段和业务表结构保持现有协议。
- 仓库来源说明、版权声明及历史资料中的旧名保留。

## 验证命令

在 `knowflow/` 中运行：

```powershell
.\mvnw.cmd clean compile -DskipTests
.\mvnw.cmd -Dtest=KnowFlowApplicationTests test
```

在 `ui/` 中运行：

```powershell
npm run build
npm run lint
```

上下文测试不等同于真实数据库或外部模型端到端验证。`AgentRuntimeV1Test`、`AgentRuntimeV2Test` 会调用外部聊天模型，不作为默认验证命令。

## 二次开发说明

展示或用于简历时，应分别说明原项目基础和个人贡献，并用实际提交记录、源码和验证结果支撑。项目改名本身不作为技术成果。
