import WarezNetLogo from '__IMG/FloppyKeygen.png'
import DiscordPixelLogo from '__IMG/DISCORD_PIXEL.png'
import './App.css'

function App() {
    return (
        <>
            <header>
                <div className="logo"> {/* Исправлено class на className */}
                    <img className="LogoIMGs" src={WarezNetLogo} alt="WarezNET Logo" /> {/* Добавлен alt */}
                    <span>WarezNET</span> {/* Текст вынесен в отдельный span */}
                </div>
                <div className="socials"> {/* Исправлено class на className и Socials на socials */}
                    <a href="https://discord.gg/wh53QzXSJa">
                        <img className="LogoIMGs" src={DiscordPixelLogo} alt="Discord" /> {/* Добавлен alt */}
                    </a>
                </div>
            </header>
            <content>
                <nav>

                </nav>
                <div>

                </div>
            </content>
        </>
    )
}

export default App