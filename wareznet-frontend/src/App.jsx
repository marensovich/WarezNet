import WarezNetLogo from '__IMG/FloppyKeygen.png'
import DiscordPixelLogo from '__IMG/DISCORD_PIXEL.png'
import './App.css'

function App() {
    return (
        <>
            <header>
                <div className="logo"> {/* Исправлено class на className */}
                    <a className="LogoText" href="https://warez.net">
                    <img className="LogoIMGs" src={WarezNetLogo} alt="WarezNET Logo" /> {/* Добавлен alt */}
                    <span className="LogoText">WarezNET</span> {/* Текст вынесен в отдельный span */}
                    </a>
                </div>
                <div className="socials"> {/* Исправлено class на className и Socials на socials */}
                    <a href="https://discord.gg/wh53QzXSJa">
                        <img className="LogoIMGs" src={DiscordPixelLogo} alt="Discord" /> {/* Добавлен alt */}
                    </a>
                </div>
            </header>
            <content>
                <nav>
                    <ul className="NB_Menu">
                        <li>Softwarez</li>
                        <li>Operating Systems</li>
                        <li>Images</li>
                    </ul>
                </nav>
                <div>

                </div>
            </content>
        </>
    )
}

export default App