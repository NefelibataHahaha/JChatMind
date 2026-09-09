import { BrowserRouter } from "react-router-dom";
import AppLayout from "./components/AppLayout.tsx";
import { ChatSessionsProvider } from "./contexts/ChatSessionsContext.tsx";

function App() {
  return (
    <BrowserRouter>
      <ChatSessionsProvider>
        <AppLayout />
      </ChatSessionsProvider>
    </BrowserRouter>
  );
}

export default App;
